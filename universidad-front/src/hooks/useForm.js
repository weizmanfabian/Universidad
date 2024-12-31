/**
 * Hook para manejar formularios
 * @param {Object} initialForm Formulario inicial
 * @param {Function} validateForm Función para validar el formulario
 * @returns {Object} Objeto con las siguientes propiedades:
 *  - form: estado del formulario
 *  - setForm: función para actualizar el formulario
 *  - errors: errores del formulario
 *  - setErrors: función para actualizar los errores
 *  - handleChange: función para manejar cambios en el formulario
 *  - handleBlur: función para manejar el evento blur en el formulario
 *  - handleSubmit: función para manejar el envío del formulario
 *  - isSubmitting: estado de envío del formulario
 *  - setIsSubmitting: función para actualizar el estado de envío
 *  - validateErrors: función para validar errores en un campo
 */
export const useForm = (initialForm, validateForm) => {
  const [form, setForm] = useState(initialForm);
  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  /**
   * Función para validar errores en un campo
   * @param {string} value Valor del campo
   * @param {Array} expresionesRegulares Expresiones regulares para validar
   * @param {Array} messages Mensajes de error
   * @returns {string} Error del campo
   */
  const validateErrors = (value, expresionesRegulares, messages) => {
    const results = expresionesRegulares.map((ex, index) => {
      if (!ex.test(value)) {
        return messages[index];
      }

      return "";
    });

    return results.find((result) => result !== "");
  };

  /**
   * Función para manejar cambios en el formulario
   * @param {Event} e Evento de cambio
   * @param {Function} callback Función a ejecutar después de cambiar el formulario
   */
  const handleChange = async (e, callback) => {
    const { name, value } = e.target;

    setForm((prevForm) => ({
      ...prevForm,
      [name]: value,
    }));

    if (callback) await callback();
  };

  /**
   * Función para manejar el evento blur en el formulario
   * @param {Event} e Evento de blur
   * @param {Function} callback Función a ejecutar después de cambiar el formulario
   */
  const handleBlur = async (e, callback) => {
    handleChange(e);
    setErrors(validateForm(form));

    if (callback) await callback();
  };

  /**
   * Función para manejar el envío del formulario
   * @param {Event} e Evento de envío
   * @param {Function} sendPostPutForm Función a ejecutar para enviar el formulario
   * @param {Function} actionPrev Función a ejecutar antes de enviar el formulario
   * @param {Function} actionPost Función a ejecutar después de enviar el formulario
   */
  const handleSubmit = async (
    e,
    sendPostPutForm,
    actionPrev = null,
    actionPost = null
  ) => {
    e.preventDefault();
    const errResult = await validateForm(form);
    if ((errResult && Object.values(errResult).some((error) => error !== "")) ||
      JSON.stringify(form) !== JSON.stringify(initialForm)) {
      try {
        setIsSubmitting(true);

        if (actionPrev) await actionPrev();

        await sendPostPutForm();

        if (actionPost) await actionPost();

      } catch (err) {
        console.error(`Ocurrió un error: ${err}`);
      } finally {
        setIsSubmitting(false);
      }
    } else {
      setErrors(errResult);
      return false;
    }
  };

  return {
    form,
    setForm,
    errors,
    setErrors,
    handleChange,
    handleBlur,
    handleSubmit,
    isSubmitting,
    setIsSubmitting,
    validateErrors,
  };
};

