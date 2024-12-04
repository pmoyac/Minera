/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', () => {
    const cerrarSesionBtns = document.querySelectorAll('.cerrarsesion');
    cerrarSesionBtns.forEach(btn => {
        btn.addEventListener('click', function () {
            localStorage.removeItem('token');
            sessionStorage.removeItem('token');
            window.location.href = 'login.html';
        });
    });
});

