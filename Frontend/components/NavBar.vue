<!-- NavBar: Barra de navegación -->
<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <!-- NavBar: Implementacion de menú de navegación -->
        <a class="navbar-brand" href="/home">Home</a>
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <!-- NavBar: Elementos de navegacion a otras paginas relacionadas -->
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="/perfil">Perfil</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/home">Emergencias</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/emergencia">Crear emergencia</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/tarea">Tareas</a>
            </li>
            <li class="nav-item">
              <button @click="logout" class="btn btn-danger">
                Cerrar sesión
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </template>
  <!-- Exportamos componente para poder realizar cierre de sesion mediante "logout" -->
  <script>
  export default {
    methods: {
      logout() {
        //Limpiamos almacenamiento local
        localStorage.clear()
        //Redireccionamiento a la pagina principal
        window.location.href = '/'
      },
    },
  }
  </script>
  <style scoped>
  nav {
    background-color: aqua;
  }

  #logo {
    width: 25px;
    padding-bottom: 10%;
  }
  </style>
