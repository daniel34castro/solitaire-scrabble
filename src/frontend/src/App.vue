
<template>
  <SolitaireScrabble/>
</template>




<script>
import SolitaireScrabble from './components/SolitaireScrabble.vue'


export default {
  name: 'App',
  components: {
    SolitaireScrabble,
  }
}
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* padding-top: 60px; */
  height: 100%;
}
</style>