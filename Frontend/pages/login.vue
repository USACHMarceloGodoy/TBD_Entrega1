
<!-- Vista del login -->
<template>
  <section class=" text-center text-lg-start">
  <style>
    .rounded-t-5 {
      border-top-left-radius: 0.5rem;
      border-top-right-radius: 0.5rem;
      
    }

    @media (min-width: 992px) {
      .rounded-tr-lg-0 {
        border-top-right-radius: 0;
      }

      .rounded-bl-lg-5 {
        border-bottom-left-radius: 0.5rem;
      }
    }
  </style>

  <div class="card mb-3">
    <div class="row g-0 d-flex align-items-center">
      <div class="col-lg-4 d-none d-lg-flex">
        <img src="https://img.freepik.com/foto-gratis/grupo-voluntarios-felices-diversos_53876-15117.jpg?size=626&ext=jpg&ga=GA1.1.1016474677.1696982400&semt=ais" alt="Trendy Pants and Shoes"
          class="w-100 rounded-t-5 rounded-tr-lg-0 rounded-bl-lg-5" />
      </div>
      <div class="col-lg-8">
        <div class="card-body py-5 px-md-5">

          <form>
            <!-- Email input -->
            <div class="form-outline mb-4">
              <input type="email" id="form2Example1" class="form-control" />
              <label class="form-label" for="form2Example1">RUT</label>
            </div>

            <!-- Password input -->
            <div class="form-outline mb-4">
              <input type="password" id="form2Example2" class="form-control" />
              <label class="form-label" for="form2Example2">Contraseña</label>
            </div>

            <!-- 2 column grid layout for inline styling -->
            <div class="row mb-4">
              <div class="col d-flex justify-content-center">
                <!-- Checkbox -->
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />
                  <label class="form-check-label" for="form2Example31"> Recordar Usuario </label>
                </div>
              </div>

              <div class="col">
                <!-- Simple link -->
                <a href="#!">¿Olvidaste tu contraseña?</a>
              </div>
            </div>

            
            <button type="button" class="btn btn-primary btn-block mb-4">INICIAR SESIÓN</button>

          </form>

        </div>
      </div>
    </div>
  </div>
</section>
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
