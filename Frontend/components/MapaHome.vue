<!-- MapaHome : Creacion de mapa interactivo -->

<!-- Estructura de HTML -->
<template>
    <div id="map">
      <l-map :zoom="zoom" :center="center">
        <l-tile-layer :url="url"></l-tile-layer>
  
        <l-marker
          v-for="(marker, index) in puntos"
          :key="index"
          :lat-lng="marker.latLng"
        >
          <l-popup>{{ marker.name }}</l-popup>
        </l-marker>
      </l-map>
    </div>
  </template>
  
  <!-- Importamos componentes necesarios para el uso adecuado de MapaHome -->
  <script>
  import { LMap, LTileLayer, LMarker, LPopup } from 'vue2-leaflet'
  
  export default {
    components: {
      LMap,
      LTileLayer,
      LMarker,
      LPopup,
    },
    props: {
      puntos: null,
    },
     //Configuracion inicial del mapa
    data() {
      return {
        zoom: 6,
        center: [-33.447487, -70.673676],
        url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
        markers: [
          // { latLng: [-33.449272, -70.655167], name: 'Punto 1' },
          // { latLng: [-33.448917, -70.663264], name: 'Punto 2' },
        ],
      }
    },
  }
  </script>
  
  <!-- MapaHome : Tamaño en pixeles del mapa a mostrar -->
  <style scoped>
  #map {
    width: 600px;
    height: 600px;
  }
  </style>
