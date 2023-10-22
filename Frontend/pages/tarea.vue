<template>
  <div class='tarea-page'>
    <NavBar />
    <div id="tareasContent">
      <div>
        <br>
        <h1 class="text-left">Tareas</h1>
        <section>
          <br>
          <div class="d-flex justify-content-between align-items-center mb-3">
            <h2 class="text-left">Filtrar tareas por región</h2>
            <div>
              <select class="form-select" v-model="regionFiltrar">
                <option disabled selected hidden>Seleccione una región</option>
                <option v-for="objeto in regionesChile" :key="objeto">
                  {{ objeto }}
                </option>
              </select>
              <button class="btn btn-primary ml-3" @click="cargarTareasFiltradas">
                Filtrar 🔍
              </button>
            </div>
          </div>
          <MapaHome :puntos="puntos" />
        </section>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
import NavBar from '../components/NavBar.vue'
import MapaHome from '~/components/MapaHome.vue'
export default {
  components: { NavBar, MapaHome },
  data() {
    return {
      // Filtrar tareas por region
      regionFiltrar: '',

      tareasFiltradas: null,
      puntos: [],

      // Select de regiones
      regionesChile: [
        'Arica y Parinacota',
        'Tarapacá',
        'Antofagasta',
        'Atacama',
        'Coquimbo',
        'Valparaíso',
        'Metropolitana',
        "O'Higgins",
        'Maule',
        'Ñuble',
        'Biobío',
        'La Araucanía',
        'Los Ríos',
        'Los Lagos',
        'Aysén',
        'Magallanes',
      ],
    }
  },
  methods: {
    async cargarTareasFiltradas() {
      if (this.regionFiltrar == undefined) {
        alert('Debe escoger una región')
      }
      try {
        this.puntos = []
        const response = await axios.get(
          `http://localhost:8080/api/tareas/region`,
          {
            params: {
              region: this.regionFiltrar,
            },
          }
        )
        console.log('Tareas filtradas pedidas: ', response)
        this.tareasFiltradas = response.data
        for (let i = 0; i < this.tareasFiltradas.length; i++) {
          const tarea = this.tareasFiltradas[i]
          const punto = {
            latLng: [tarea.latitud, tarea.longitud],
            name: tarea.nombre,
          }
          this.puntos.push(punto)
        }
      } catch (error) {}
    },
  },
}
</script>
<style>
#tareasContent {
  display: flex;
  align-items: flex-start;
  justify-content: center;
}

.tarea-page {
  background-image: url('/fondo.jpg');
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  min-height: 100vh;
}

/* Estilos personalizados para los títulos */
h1.text-left {
  text-align: left;

}

h2.text-left {
  margin-right: 20px;

}
</style>
