import React, { useEffect, useState } from 'react'
import TemplateAdmin from './TemplateAdmin'
import axios from 'axios';
import { faPencil, faPlus, faTrash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';
import Swal from 'sweetalert2';

const Estudiante = ({ URL }) => {
    const [estudiantes, setEstudiantes] = useState([]);

    const getEstudiantes = async () => {
        try {
            const response = await axios.get(`${URL}/estudiantes`);
            setEstudiantes(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    const deleteEstudiante = async (id) => {
        try {
            await axios.delete(`${URL}/estudiantes/${id}`);
            getEstudiantes();
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
        getEstudiantes();
    }, []);

    return (
        <TemplateAdmin>
            <Link to="/app/estudiantes/new" className="btn btn-primary">
                <FontAwesomeIcon icon={faPlus} className="me-2" />
                Nuevo
            </Link>
            <div className='d-flex justify-content-center'>
                <h1>Estudiantes</h1>
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
                    {estudiantes.map((estudiante) => (
                        <tr key={estudiante.id}>
                            <th scope="row">{estudiante.id}</th>
                            <td>{estudiante.nombre}</td>
                            <td>
                                <Link to={`/app/estudiantes/${estudiante.id}`}>
                                    <FontAwesomeIcon icon={faPencil} className="me-2" />
                                </Link>
                            </td>
                            <td>
                                <FontAwesomeIcon
                                    icon={faTrash}
                                    onClick={() => {
                                        Swal.fire({
                                            title: `¿Estás seguro de eliminar a ${estudiante.nombre}?`,
                                            text: "No habrá marcha atrás, así que pilas!",
                                            icon: "warning",
                                            showCancelButton: true,
                                            confirmButtonColor: "#3085d6",
                                            cancelButtonColor: "#d33",
                                            confirmButtonText: "Si, eliminar!"
                                        }).then(async (result) => {
                                            if (result.isConfirmed) {
                                                deleteEstudiante(estudiante.id)
                                            }
                                        });
                                    }} />
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </TemplateAdmin>
    )
}

export default Estudiante
