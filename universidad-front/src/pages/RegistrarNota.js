import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useState } from 'react';
import { useForm } from '../hooks/useForm';
import { Link, useNavigate, useParams } from 'react-router-dom';
import TemplateAdmin from './TemplateAdmin';
import axios from 'axios';
import Swal from 'sweetalert2';
import { Input, Select } from '../components/forms/Input';

const RegistrarNota = ({ URL }) => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [estudiantes, setEstudiantes] = useState([]);
    const [profesores, setProfesores] = useState([]);

    const initialForm = {
        idProfesor: "",
        idEstudiante: "",
        valor: "",
    };

    const validateForm = (form) => {
        const errors = {};
        if (!form.idProfesor) errors.idProfesor = "Debe seleccionar un profesor.";
        if (!form.idEstudiante) errors.idEstudiante = "Debe seleccionar un estudiante.";
        if (!form.valor || isNaN(form.valor) || form.valor < 1 || form.valor > 10) {
            errors.valor = "El valor debe ser un número entre 1 y 10.";
        }
        return errors;
    };

    const {
        form,
        setForm,
        errors,
        setErrors,
        handleChange,
        handleSubmit,
        handleBlur,
        isSubmitting,
    } = useForm(initialForm, validateForm);

    const saveNota = async () => {
        try {
            const endpoint = id ? `${URL}/notas/${id}` : `${URL}/notas`;
            const method = id ? axios.put : axios.post;

            await method(endpoint, form);

            Swal.fire({
                position: "top-end",
                icon: "success",
                title: id ? "Nota actualizada" : "Nota registrada",
                showConfirmButton: false,
                toast: true,
                timer: 1500,
            });

            navigate("/app/notas");
        } catch (error) {
            console.error(error);
            if (error.response?.data?.errors) {
                setErrors((prevErrors) => ({
                    ...prevErrors,
                    ...error.response.data.errors,
                }));
            } else {
                Swal.fire({
                    position: "top-end",
                    icon: "error",
                    title: "Error al guardar la nota",
                    showConfirmButton: true,
                });
            }
        }
    };

    useEffect(() => {
        if (id) {
            axios.get(`${URL}/notas/${id}`).then((response) => {
                console.log(response.data);
                setForm({
                    id: response.data.id,
                    idEstudiante: String(response.data.estudiante.id),
                    idProfesor: String(response.data.profesor.id),
                    valor: response.data.valor,
                });
            });
        }
    }, [id]);

    useEffect(() => {
        axios.get(`${URL}/estudiantes`).then((response) => setEstudiantes(response.data));
        axios.get(`${URL}/profesores`).then((response) => setProfesores(response.data));
    }, []);

    return (
        <TemplateAdmin>
            <Link to="/app/notas" className="btn btn-secondary">
                <FontAwesomeIcon icon={faArrowLeft} className="me-2" />
                Volver
            </Link>
            <div className="card w-50 mx-auto mt-3 p-4 shadow-sm">
                <div className="card-body">
                    <h1 className="text-center mb-4">{id ? "Editar" : "Registrar"} Nota</h1>
                    <form>
                        <Select
                            items={estudiantes}
                            classNameDiv=""
                            label="Estudiante"
                            classNameLabel=""
                            value={form.idEstudiante + ""}
                            name="idEstudiante"
                            classNameSelect={`form-select ${errors.idEstudiante ? "is-invalid" : ""}`}
                            required={true}
                            onChange={handleChange}
                            onBlur={handleBlur}
                            err={errors.idEstudiante}
                            itemKey="id"
                            itemLabel={(item) => `${item.nombre}`}
                        />
                        <Select
                            items={profesores}
                            classNameDiv=""
                            label="Profesor"
                            classNameLabel=""
                            value={String(form.idProfesor)}
                            name="idProfesor"
                            classNameSelect={`form-select ${errors.idProfesor ? "is-invalid" : ""}`}
                            required={true}
                            onChange={handleChange}
                            onBlur={handleBlur}
                            err={errors.idProfesor}
                            itemKey="id"
                            itemLabel={item => `${item.nombre}`}
                        />
                        <Input
                            classNameDiv="col-md-12"
                            classNameInput={`form-control ${errors.valor ? "is-invalid" : ""}`}
                            label="Valor"
                            type="text"
                            required
                            onBlur={handleBlur}
                            onChange={handleChange}
                            name="valor"
                            value={form.valor}
                            err={errors.valor}
                        />
                        <div className="text-center">
                            <button
                                type="submit"
                                className="btn btn-primary w-50"
                                onClick={(e) => handleSubmit(e, saveNota)}
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

export default RegistrarNota;
