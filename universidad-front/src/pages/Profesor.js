import React, { useEffect, useState } from 'react';
import TemplateAdmin from './TemplateAdmin';
import axios from 'axios';
import { faPencil, faPlus, faTrash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';
import Swal from 'sweetalert2';

const Profesor = ({ URL }) => {
    const [profesores, setProfesores] = useState([]);

    const getProfesores = async () => {
        try {
            const response = await axios.get(`${URL}/profesores`);
            setProfesores(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    const deleteProfesor = async (id) => {
        try {
            await axios.delete(`${URL}/profesores/${id}`);
            getProfesores();
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Registro eliminado",
                showConfirmButton: false,
                toast: true,
                timer: 1500,
            });
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getProfesores();
    }, []);

    return (
        <TemplateAdmin>
            <Link to="/app/profesores/new" className="btn btn-primary">
                <FontAwesomeIcon icon={faPlus} className="me-2" />
                Nuevo
            </Link>
            <div className='d-flex justify-content-center'>
                <h1>Profesores</h1>
            </div>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    {profesores.map((profesor) => (
                        <tr key={profesor.id}>
                            <th scope="row">{profesor.id}</th>
                            <td>{profesor.nombre}</td>
                            <td>
                                <Link to={`/app/profesores/${profesor.id}`}>
                                    <FontAwesomeIcon icon={faPencil} className="me-2" />
                                </Link>
                            </td>
                            <td>
                                <FontAwesomeIcon
                                    icon={faTrash}
                                    onClick={() => {
                                        Swal.fire({
                                            title: `¿Estás seguro de eliminar a ${profesor.nombre}?`,
                                            text: "No habrá marcha atrás, así que pilas!",
                                            icon: "warning",
                                            showCancelButton: true,
                                            confirmButtonColor: "#3085d6",
                                            cancelButtonColor: "#d33",
                                            confirmButtonText: "Si, eliminar!"
                                        }).then(async (result) => {
                                            if (result.isConfirmed) {
                                                deleteProfesor(profesor.id);
                                            }
                                        });
                                    }}
                                />
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </TemplateAdmin>
    );
};

export default Profesor;
