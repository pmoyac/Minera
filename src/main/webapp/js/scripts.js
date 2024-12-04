/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', () => {
    // Obtener el token del almacenamiento
    const token = localStorage.getItem('token'); // O sessionStorage/cookie si lo usas

    if (!token) {
        // Redirigir al usuario si no hay token
        window.location.href = 'login.html';
        return;
    }

    try {
        // Decodificar el token
        const decoded = jwt_decode(token);

        // Verificar si el token ha expirado
        const now = Date.now() / 1000; // Tiempo actual en segundos
        if (decoded.exp < now) {
            alert('Tu sesión ha expirado. Por favor, inicia sesión nuevamente.');
            localStorage.removeItem('token'); // Eliminar el token expirado
            window.location.href = 'login.html';
        }
    } catch (error) {
        console.error('Error al decodificar el token:', error);
        localStorage.removeItem('token'); // Eliminar token inválido
        window.location.href = 'login.html';
    }
});


