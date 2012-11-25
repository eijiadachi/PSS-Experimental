package br.inf.pucrio.actomatic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import br.inf.pucrio.actomatic.model.ConfigurationAction;
import br.inf.pucrio.actomatic.model.ConfigurationAction.ConfigurationType;
import br.inf.pucrio.actomatic.model.Entity;

public class Main
{
	public static <T extends Entity> Map<String, Object> describeEntity(T entity)
	{
		Map<String, Object> result = new TreeMap<String, Object>();

		Class<? extends Entity> entityClass = entity.getClass();
		Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields)
		{
			String fieldName = field.getName();
			String fieldAccessonr = String.format( "get%s%s", fieldName.substring( 0, 1 )
					.toUpperCase(), fieldName.substring( 1 ) );

			try
			{
				Method method = entityClass.getMethod( fieldAccessonr, null );

				Object fieldValue = method.invoke( entityClass, null );

				result.put( fieldName, fieldValue );
			}
			catch (NoSuchMethodException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IllegalArgumentException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public static void main(String[] args)
	{
		ConfigurationAction a = new ConfigurationAction();

		a.setDescription( "desc" );
		a.setId( 1 );
		a.setName( "name" );
		a.setSetting( 1.2 );
		a.setType( ConfigurationType.SCREEN );

		Map<String, Object> map = describeEntity( a );

		System.out.println( "resultado" );
		System.out.println( map );

		System.out.println( a.toJSON() );
	}
}
