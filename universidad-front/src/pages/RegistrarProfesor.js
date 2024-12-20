import React, { useEffect } from "react";
import { useForm } from '../hooks/useForm';
import { useParams, useNavigate, Link } from 'react-router-dom';
import axios from 'axios';
import Swal from 'sweetalert2';
import { Input } from './../components/forms/Input';
import TemplateAdmin from "./TemplateAdmin";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeft } from "@fortawesome/free-solid-svg-icons";

const RegistrarProfesor = ({ URL }) => {
    const { id } = useParams();

    const initialForm = {
        nombre: ""
    };

    const validateForm = (form) => {
        const errors = {};
        const lengthNombre = /^.{7,150}$/;

        errors.nombre = validateErrors(form.nombre, [lengthNombre], ["Debe tener una longitud entre 7 y 150 caracteres"]);

        return errors;
    };

    const {
        form,
        setForm,
        errors,
        setErrors,
        handleChange,
        handleBlur,
        handleSubmit,
        isSubmitting,
        validateErrors
    } = useForm(initialForm, validateForm);

    const saveProfesor = async () => {
        try {
            const endpoint = id ? `${URL}/profesores/${id}` : `${URL}/profesores`;
            const method = id ? axios.put : axios.post;

            if (id) {
                delete form.id;
            }

            await method(endpoint, form);

            Swal.fire({
                position: "top-end",
                icon: "success",
                title: id ? "Actualización exitosa" : "Registro exitoso",
                showConfirmButton: false,
                toast: true,
                timer: 1500,
            });

            navigate("/app/profesores");
        } catch (error) {
            console.error(error);

            if (error.response?.data?.errors) {
                setErrors(prevErrors => ({
                    ...prevErrors,
                    ...error.response.data.errors,
                }));
            } else {
                Swal.fire({
                    position: "top-end",
                    icon: "error",
                    title: "Ocurrió un error al enviar el formulario",
                    showConfirmButton: true,
                });
            }
        }
    };

    useEffect(() => {
        if (id) {
            const fetchProfesor = async () => {
                try {
                    const response = await axios.get(`${URL}/profesores/${id}`);
                    setForm(response.data);
                } catch (error) {
                    console.error(error);
                }
            };
            fetchProfesor();
        }
    }, [id]);

    const navigate = useNavigate();

    return (
        <TemplateAdmin>
            <Link to="/app/profesores" className="btn btn-secondary mb-3">
                <FontAwesomeIcon icon={faArrowLeft} className="me-2" />
                Volver
            </Link>

            <div className="card w-50 mx-auto mt-3 p-4 shadow-sm">
                <div className="card-body">
                    <h1 className="text-center mb-4">{id ? "Editar" : "Registrar"} Profesor</h1>
                    <form>
                        <Input
                            classNameDiv="col-md-12"
                            label="Nombre"
                            type="text"
                            required
                            onBlur={handleBlur}
                            name="nombre"
                            value={form.nombre || ""}
                            err={errors?.nombre}
                            onChange={handleChange}
                            classNameInput={`form-control ${errors.nombre ? "is-invalid" : ""}`}
                        />
                        <div className="text-center">
                            <button
                                type="submit"
                                className="btn btn-primary w-50"
                                onClick={(e) => handleSubmit(e, saveProfesor)}
                                disabled={isSubmitting}
                            >
                                {id ? "Editar" : "Registrar"}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </TemplateAdmin>

    );
};

export default RegistrarProfesor;
