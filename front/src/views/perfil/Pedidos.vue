<template>
  <div class="content">
    <div class="viewport">
      <md-toolbar class="md-primary">
        <span class="md-title md-toolbar-section-start">Meus Pedidos</span>
      </md-toolbar>

      <md-list class="md-double-line">
        <md-subheader>Pedido nº 123456</md-subheader>
        <md-list-item>
          <div class="md-layout-item md-large-size-10">
            <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=9363533&qld=90&l=265&a=-1" class="img-pedido" alt="People">            
          </div>
          <div class="md-layout-item md-large-size-60">
            <div class="md-list-item-text">
              <span></span>
              <span>O Orfanato da Srta. Peregrine Para Crianças Peculiares</span>
            </div>
          </div>
          <div class="md-layout-item md-large-size-15">
            <div class="md-list-item-text">
              <span>1 item</span>
            </div>
          </div>
          <div class="md-layout-item md-large-size-15">
            <div class="md-list-item-text">
              <span>R$ 18,30</span>
            </div>
          </div>
        </md-list-item>
        
        <md-list-item>
          <div class="md-layout-item md-large-size-10">
            <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=7246326&qld=90&l=265&a=-1" class="img-pedido" alt="People">            
          </div>
          <div class="md-layout-item md-large-size-60">
            <div class="md-list-item-text">
              <span></span>
              <span>A Origem Das Espécies</span>
            </div>
          </div>
          <div class="md-layout-item md-large-size-15">
            <div class="md-list-item-text">
              <span>1 item</span>
            </div>
          </div>
          <div class="md-layout-item md-large-size-15">
            <div class="md-list-item-text">
              <span>R$ 58,15</span>
            </div>
          </div>
        </md-list-item>

        <md-list-item>
          <div class="md-layout-item md-large-size-70">
            <div class="md-list-item-text">
              <span>Pedido em transporte</span>
              <span>Previsão: 30/11/2018</span>
            </div>
          </div>
          <div class="md-layout-item md-large-size-15">
            <div class="md-list-item-text">
              <span>Total:</span>
            </div>
          </div>
          <div class="md-layout-item md-large-size-15">
            <div class="md-list-item-text">
              <span>R$ 76,45</span>
            </div>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>Pedido nº 123456</md-subheader>
        <md-list-item>
          <div class="md-layout-item md-large-size-10">
            <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=1574783&qld=90&l=430&a=-1=1005808113" class="img-pedido" alt="People">            
          </div>
          <div class="md-layout-item md-large-size-60">
            <div class="md-list-item-text">
              <span></span>
              <span>A Revolução Dos Bichos</span>
            </div>
          </div>
          <div class="md-layout-item md-large-size-15">
            <div class="md-list-item-text">
              <span>1 item</span>
            </div>
          </div>
          <div class="md-layout-item md-large-size-15">
            <div class="md-list-item-text">
              <span>R$ 25,40</span>
            </div>
          </div>
        </md-list-item>

        <md-list-item>
          <div class="md-layout-item md-large-size-70">
            <div class="md-list-item-text">
              <span>Pedido Recebido</span>
              <span>Previsão: 25/11/2018</span>
            </div>
          </div>
          <div class="md-layout-item md-large-size-15">
            <div class="md-list-item-text">
              <span>Total:</span>
            </div>
          </div>
          <div class="md-layout-item md-large-size-15">
            <div class="md-list-item-text">
              <span>R$ 25,40</span>
            </div>
          </div>
        </md-list-item>

      </md-list>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/mk/components";
import {
  StatsCard,
  ChartCard,
  NavTabsCard,
  NavTabsTable,
  OrderedTable
} from '@/mk_admin/components'

export default{
  components: {
    StatsCard,
    ChartCard,
    NavTabsCard,
    NavTabsTable,
    OrderedTable,
    LoginCard
  }
}
</script>

<style>
.img-pedido{
  max-height: 50px;
}
</style>

