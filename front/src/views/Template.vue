<template>
    <div id="background">
    <div id="background-image"> </div>
        
        <form-cad-cliente></form-cad-cliente>
        
   
    </div>
</template>
<script>
import Toolbar from './Toolbar'
import FormCadCliente from './cliente/FormCadCliente'
import Footer from './Footer'
export default {
    components:{Toolbar, FormCadCliente, Footer}
}
</script>

<style>
#background{
    background-color: black;
}

#background-image{
    
    background: url('../assets/img/bg-dark12.jpg');
    background-size: 100% 100%;
     -webkit-box-shadow: inset 0 -16px 66px 40px #000;
    box-shadow: inset 0 -16px 66px 40px #000;
    height: 650px;
    position: static;
    top: 0;
}

</style>

