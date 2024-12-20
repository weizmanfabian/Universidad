import React, { useEffect, useState } from 'react';
import TemplateAdmin from './TemplateAdmin';
import axios from 'axios';
import { faPencil, faPlus, faTrash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';
import Swal from 'sweetalert2';

const Nota = ({ URL }) => {
    const [notas, setNotas] = useState([]);

    const getNotas = async () => {
        try {
            const response = await axios.get(`${URL}/notas`);
            setNotas(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    const deleteNota = async (id) => {
        try {
            await axios.delete(`${URL}/notas/${id}`);
            getNotas();
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Nota eliminada exitosamente",
                showConfirmButton: false,
                toast: true,
                timer: 1500,
            });
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getNotas();
    }, []);

    return (
        <TemplateAdmin>
            <Link to="/app/notas/new" className="btn btn-primary">
                <FontAwesomeIcon icon={faPlus} className="me-2" />
                Nueva Nota
            </Link>
            <div className='d-flex justify-content-center'>
                <h1>Notas</h1>
            </div>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Valor</th>
                        <th scope="col">Profesor</th>
                        <th scope="col">Estudiante</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    {notas.map((nota) => (
                        <tr key={nota.id}>
                            <th scope="row">{nota.id}</th>
                            <td>{nota.valor}</td>
                            <td>{nota.profesor.nombre}</td>
                            <td>{nota.estudiante.nombre}</td>
                            <td>
                                <Link to={`/app/notas/${nota.id}`}>
                                    <FontAwesomeIcon icon={faPencil} className="me-2" />
                                </Link>
                            </td>
                            <td>
                                <FontAwesomeIcon
                                    icon={faTrash}
                                    onClick={() => {
                                        Swal.fire({
                                            title: `¿Estás seguro de eliminar la nota del estudiante "${nota.estudiante.nombre}"?`,
                                            text: "No habrá marcha atrás, así que pilas!",
                                            icon: "warning",
                                            showCancelButton: true,
                                            confirmButtonColor: "#3085d6",
                                            cancelButtonColor: "#d33",
                                            confirmButtonText: "Sí, eliminar!"
                                        }).then(async (result) => {
                                            if (result.isConfirmed) {
                                                deleteNota(nota.id);
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

export default Nota;
