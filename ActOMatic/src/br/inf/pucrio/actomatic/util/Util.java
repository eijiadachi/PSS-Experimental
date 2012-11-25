package br.inf.pucrio.actomatic.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.util.Log;
import br.inf.pucrio.actomatic.model.Entity;

public final class Util
{
	private Util()
	{
		super();
	}

	@SuppressWarnings("unchecked")
	private static <T extends Entity> List<Field> getDeclaredFields(T entity)
	{
		Class<? extends Entity> class1 = entity.getClass();

		List<Field> fields = new ArrayList<Field>();

		while (class1.getSuperclass() != null)
		{

			Field[] declaredFields = class1.getDeclaredFields();

			List<Field> declaredFieldsList = Arrays.asList( declaredFields );

			fields.addAll( declaredFieldsList );

			class1 = (Class<? extends Entity>) class1.getSuperclass();
		}

		return fields;
	}

	public static <T extends Entity> Map<String, Object> describeEntity(T entity)
	{
		Map<String, Object> result = new TreeMap<String, Object>();

		List<Field> fields = getDeclaredFields( entity );

		Class<? extends Entity> entityClass = entity.getClass();

		for (Field field : fields)
		{
			String fieldName = field.getName();

			String fieldAccessor = String.format( "get%s%s", fieldName.substring( 0, 1 )
					.toUpperCase(), fieldName.substring( 1 ) );
			
			try
			{				
				Method method = entityClass.getMethod( fieldAccessor, (Class<?>[]) null );

				Object fieldValue = method.invoke( entity, (Object[]) null );

				result.put( fieldName, fieldValue );
			}
			catch (IllegalArgumentException e)
			{
				Log.d( Util.class.getCanonicalName(), e.getMessage(), e );
				throw new RuntimeException( e );
			}
			catch (IllegalAccessException e)
			{
				Log.d( Util.class.getCanonicalName(), e.getMessage(), e );
				throw new RuntimeException( e );
			}
			catch (NoSuchMethodException e)
			{
				Log.d( Util.class.getCanonicalName(), e.getMessage(), e );
				throw new RuntimeException( e );
			}
			catch (InvocationTargetException e)
			{
				Log.d( Util.class.getCanonicalName(), e.getMessage(), e );
				throw new RuntimeException( e );
			}
		}

		return result;
	}
}
