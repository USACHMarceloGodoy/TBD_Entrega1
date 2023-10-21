<template>
  <div
    id="index"
    class="d-flex flex-column justify-content-center align-items-center vh-100"
  >
    <h1 class="text-center">Plataforma de Emergencia para Voluntariados</h1>
    <div class="mt-4">
      <a href="/login" class="btn btn-success">Iniciar sesión</a>
      <a href="/register" class="btn btn-secondary">Registrarte</a>
    </div>
  </div>
</template>

<script>
export default {}
</script>
<style scooped>
#index {
  color: white;
  background: rgb(2, 0, 36);
  background: linear-gradient(
    0deg,
    rgb(92, 64, 64) 0%,
    rgb(95, 61, 61) 5%,
    rgb(104, 59, 59) 10%,
    rgb(97, 58, 58) 17%,
    rgb(114, 53, 53) 24%,
    rgb(255, 0, 0) 100%
  );
}
</style>
