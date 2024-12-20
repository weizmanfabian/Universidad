import React, { useEffect, useState } from 'react';
import TemplateAdmin from "../pages/TemplateAdmin";
import { Link } from 'react-router-dom';
import { faHome, faGraduationCap, faUser, faUsers, faStickyNote } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import Swal from 'sweetalert2';
import axios from 'axios';
import Card from '../components/Card';



const HomeApp = ({ URL }) => {


    useEffect(() => {
    }, []);

    return (
        <TemplateAdmin>
            <div className='row'>
                <Card
                    classNameCard={"col-sm-4 mb-3 mb-sm-0 text-center"}
                    title={"Estudiantes"}
                    description={"Gestión de estudiantes"}
                    url={"/app/estudiantes"}
                    classNameButton={"btn btn-primary w-50"}
                    textButton={"Ver"}
                    icon={faGraduationCap}
                />
                <Card
                    classNameCard={"col-sm-4 mb-3 mb-sm-0 text-center"}
                    title={"Profesores"}
                    description={"Gestión de profesores"}
                    url={"/app/profesores"}
                    classNameButton={"btn btn-primary w-50"}
                    textButton={"Ver"}
                    icon={faUsers}
                />
                <Card
                    classNameCard={"col-sm-4 mb-3 mb-sm-0 text-center"}
                    title={"Notas"}
                    description={"Gestión de notas"}
                    url={"/app/notas"}
                    classNameButton={"btn btn-primary w-50"}
                    textButton={"Ver"}
                    icon={faStickyNote}
                />
            </div>
        </TemplateAdmin>
    );
}

export default HomeApp;
