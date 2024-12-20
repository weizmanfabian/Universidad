import { Route, Routes } from "react-router-dom";
import HomeApp from "../pages/Home.js";
import Navbar from "../components/Navbar.js"
import Estudiante from "../pages/Estudiante.js";
import RegistrarEstudiante from "../pages/RegistrarEstudiante.js";
import Profesor from "../pages/Profesor.js";
import RegistrarProfesor from "../pages/RegistrarProfesor.js";
import Nota from "../pages/Nota.js";
import RegistrarNota from "../pages/RegistrarNota.js";

const AplicationRoute = ({ user, setUser, URL }) => {
    return (
        <>
            <Navbar user={user} setUser={setUser} />
            <Routes>
                <Route path="/home" element={<HomeApp user={user} URL={URL} />} />
                <Route path="/estudiantes" element={<Estudiante URL={URL} />} />
                <Route path="/estudiantes/new" element={<RegistrarEstudiante URL={URL} />} />
                <Route path="/estudiantes/:id" element={<RegistrarEstudiante URL={URL} />} />
                <Route path="/profesores" element={<Profesor URL={URL} />} />
                <Route path="/profesores/new" element={<RegistrarProfesor URL={URL} />} />
                <Route path="/profesores/:id" element={<RegistrarProfesor URL={URL} />} />
                <Route path="/notas" element={<Nota URL={URL} />} />
                <Route path="/notas/new" element={<RegistrarNota URL={URL} />} />
                <Route path="/notas/:id" element={<RegistrarNota URL={URL} />} />
            </Routes>
        </>
    );
}

export default AplicationRoute;