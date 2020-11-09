package com.busreseravtionsystem.busreservation.exception;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.busreseravtionsystem.busreservation.config.PropertiesConfig;

@Component
public class BSRExecption {

	private static PropertiesConfig propertiesConfig;

	@Autowired
	public BSRExecption(PropertiesConfig propertiesConfig) {
		BSRExecption.propertiesConfig = propertiesConfig;
	}

	/**
	 * @param msg
	 * @param args
	 * @return
	 */
	public static RuntimeException thowException(String msg, String... args) {

		return new RuntimeException(format(msg, args));
	}

	/**
	 * @param msg
	 * @param args
	 * @return
	 */
	private static String format(String msg, String... args) {
		Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(msg));
		if (templateContent.isPresent()) {
			return MessageFormat.format(templateContent.get(), (Object[]) args);

		}
		return String.format(msg, (Object[]) args);
	}

	public static class EntityNotFoundException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EntityNotFoundException(String message) {

			super(message);
		}

	}

	/**
	 * @param entityType
	 * @param exceptionType
	 * @param id
	 * @param args
	 * @return
	 */
	public static RuntimeException thRuntimeExceptionWithId(EntityType entityType, ExceptionType exceptionType,
			Integer id, String... args) {

		String messageTemplate = getMessageTemplate(entityType, exceptionType).concat(".").concat(id.toString());
		return throwException(exceptionType, messageTemplate, args);
	}

	/**
	 * @param exceptionType
	 * @param messageTemplate
	 * @param args
	 * @return
	 */
	private static RuntimeException throwException(ExceptionType exceptionType, String messageTemplate, String[] args) {

		if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {

			return new EntityNotFoundException(format(messageTemplate, args));

		} else if (ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {

			return new DuplicateEntityException(format(messageTemplate, args));
		}

		return new RuntimeException(format(messageTemplate, args));
	}

	public static class DuplicateEntityException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DuplicateEntityException(String message) {
			super(message);
		}
	}

	/**
	 * @param entityType
	 * @param exceptionType
	 * @param args
	 * @return
	 */
	public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
		String messageTemplate = getMessageTemplate(entityType, exceptionType);
		return throwException(exceptionType, messageTemplate, args);
	}

	/**
	 * @param entityType
	 * @param exceptionType
	 * @return
	 */
	private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {

		return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
	}

	/**
	 * @param entityType
	 * @param exceptionType
	 * @param msgTemplate
	 * @param args
	 * @return
	 */
	private static RuntimeException throwExceptionWithTemplate(EntityType entityType, ExceptionType exceptionType,
			String msgTemplate, String... args) {
		return throwException(exceptionType, msgTemplate, args);

	}
}
