<!-- Estructura de HTML -->
<template>
    <div id="map">
      <l-map :zoom="zoom" :center="center" @click="handleMapClick">
        <l-tile-layer :url="url"></l-tile-layer>
        <l-marker v-if="selectedCoordinates" :lat-lng="selectedCoordinates">
        </l-marker>
      </l-map>
    </div>
  </template>

  <!-- Importamos componentes necesarios para el uso adecuado de MapaRegistro -->
  <script>
  import { LMap, LTileLayer, LMarker } from 'vue2-leaflet'

  export default {
    components: {
      LMap,
      LTileLayer,
      LMarker,
    },
    //Configuracion inicial del mapa
    data() {
      return {
        zoom: 6,
        center: [-33.447487, -70.673676],
        url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
        //Array de marcadores y almacenamiento de ubicaciones
        markers: [],
        selectedCoordinates: null,
      }
    },
    //Evento de clickeo mapa
    methods: {
      handleMapClick(event) {
        //Captura de las coordenadas en el mapa
        this.selectedCoordinates = event.latlng
        this.$emit('coordinatesSelected', this.selectedCoordinates)
      },
    },
  }
  </script>

    <!-- MapaRegistro : Tamaño en pixeles del mapa a mostrar -->
  <style scoped>
  #map {
    width: 600px;
    height: 600px;
  }
  </style>
