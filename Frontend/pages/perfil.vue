<template>
  <div>
    <NavBar/>
    <h1 style='text-align: center'>Perfil de voluntario</h1>
    <br>
    <table class="table table-striped d-flex justify-content-center">
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

<style scoped>

</style>
