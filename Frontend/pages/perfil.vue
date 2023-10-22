<template>
  <div class='perfil-page'>
    <NavBar/>
    <br>
    <h1 class='titulo' style='text-align: center'>Perfil de voluntario</h1>
    <br>
    <table class="table table-striped d-flex  table-container">
      <tbody>
        <tr>
          <th>ID</th>
<!--          <td>{{voluntario.id}}}</td>          (comentadas para ver la vista perfil)-->

        </tr>
        <tr>
          <th>RUT</th>
<!--          <td>{{voluntario.rut}}}</td>-->
        </tr>
        <tr>
          <th>Nombres</th>
<!--          <td>{{voluntario.nombres}}}</td>-->
        </tr>
        <tr>
          <th>Apellidos</th>
<!--          <td>{{ voluntario.apellidos }}</td>-->
        </tr>
        <tr>
          <th>Fecha de Nacimiento</th>
<!--          <td>{{ voluntario.fecha_nac.slice(0, 10) }}</td>-->
        </tr>
        <tr>
          <th>Disponibilidad</th>
<!--          <td>{{ voluntario.disponibilidad }}</td>-->
        </tr>
        <tr>
          <th>Teléfono</th>
<!--          <td>{{ voluntario.telefono }}</td>-->
        </tr>
        <tr>
          <th>Rol</th>
<!--          <td>{{ voluntario.rol }}</td>-->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import NavBar from '~/components/NavBar.vue' //se importa el componente de Navbar creado previamente
export default {
// Componente de la página principal
  components: {
    NavBar,
  },
  data() {
    return {
      voluntario: JSON.parse(localStorage.getItem('client')),
    }
  },
}
</script>

<style>
  .perfil-page{
    background-image: url('/fondo.jpg');
    background-size: cover; /* Ajusta el tamaño de la imagen para cubrir el contenedor */
    background-repeat: no-repeat; /* Evita que la imagen se repita */
    background-position: center; /* Centra la imagen en el contenedor */
    min-height: 100vh;

    .table-container {
      float: left;
      padding-left: 200px; /* Agrega un espaciado alrededor de la tabla */
    }

  .titulo{
    background: #ffffff;
  }
}

</style>
