
<!-- Vista del login -->
<template>
    <div class="centered-container">
      <div class="card mb-3">
        <div class="row g-0 d-flex align-items-center">
          <div class="col-lg d-none d-lg-flex">
            <img src="/voluntarios.png" class="w-100 rounded-t-5 rounded-tr-lg-0  rounded-bl-lg-5 " />
          </div>
          <div class="col-lg-8" >
            <div class="card-body py-5 px-md-5">
              <h2 class="mb-4">INGRESA</h2>
              <form>
                <div class="form-outline mb-4">
                  <input type="text" id="form2Example1" class="form-control" />
                  <label class="form-label" for="form2Example1">RUT</label>
                </div>
                <!-- Password input -->
                <div class="form-outline mb-4">
                  <input type="password" id="form2Example2" class="form-control" />
                  <label class="form-label" for="form2Example2">Contraseña</label>
                </div>
                <button type="button" class="btn btn-danger btn-block mb-4">INICIAR SESIÓN</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      rut: '',
      password: '',
    }
  },
  methods: {
    async sumbit() {
      if (this.rut.length < 10) {
        alert('Escriba un rut válido')
        return
      }

      if (!this.password) {
        alert('Escriba su contraseña')
        return
      }

      try {
        const response = await axios.post(
          'http://localhost:8080/api/voluntarios/login',
          {
            rut: this.rut,
            contrasena: this.password,
          },
          {
            headers: {
              'Content-Type': 'application/json',
            },
            credentials: 'include',
          }
        )
        console.log('respuesta: ', response)
        if (response.status === 200) {
          console.log('Sesión iniciada con éxito')
          localStorage.setItem('jwt', response.data)
        }
      } catch (error) {
        alert('Datos incorrectos')
        return
      }

      try {
        console.log('Pidiendo datos de cliente...')
        const response = await axios.get(
          'http://localhost:8080/api/voluntarios/client',
          { params: { rut: this.rut } }
        )
        localStorage.setItem('client', JSON.stringify(response.data))
        window.location.href = '/home'
      } catch (error) {
        console.log(error)
      }
    },
  },
}
</script>

<style>
  .rounded-t-5 {
    border-top-left-radius: 0.5rem;
    border-top-right-radius: 0.5rem;
  }

  @media (min-width: 1200px) {
    .rounded-tr-lg-0 {
      border-top-left-radius: 0.5rem;
    }

    .rounded-bl-lg-5 {
      border-bottom-left-radius: 0.5rem;
    }
  }

  /* Estilos para centrar en la página */
  .centered-container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    /* Agregar imagen de fondo */
    background-image: url('/fondo.jpg');
    background-size: cover; /* Ajusta el tamaño de la imagen para cubrir el contenedor */
    background-repeat: no-repeat; /* Evita que la imagen se repita */
    background-position: center; /* Centra la imagen en el contenedor */
  }
  .centered-container img{
    padding-left: 50px;
  }

</style>








