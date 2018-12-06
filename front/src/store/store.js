import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import mutations from "./mutatios";
import tipoLogradouros from "./modulos/tipoLogradouro/main";
import tipoResidencia from "./modulos/tipoResidencia/main";
import bandeiras from "./modulos/bandeiras/main";
import paises from "./modulos/paises/main";
import cliente from "./modulos/cliente/main";

Vue.use(Vuex);

export default new Vuex.Store({
  state,
  mutations,
  modules:{
    paises,
    tipoLogradouros,
    tipoResidencia,
    bandeiras,
    cliente
  }
  
});
