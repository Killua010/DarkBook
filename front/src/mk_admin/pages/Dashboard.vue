<template>
  <div class="content">
    <div class="viewport">
      <md-toolbar class="md-primary">
        <span class="md-title">Dados Pessoais</span>
        <div class="md-toolbar-section-end">
          <md-button class="md-primary"><md-icon>edit</md-icon>  Editar  </md-button>
        </div>
      </md-toolbar>

      <md-list class="md-double-line">
        <md-subheader>Nome e genero</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">face</md-icon>

          <div class="md-list-item-text">
            <span>Daniel Dias de Souza</span>
            <span>Masculino</span>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>Email</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">email</md-icon>

          <div class="md-list-item-text">
            <span>aliconnors@example.com</span>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>CPF</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">picture_in_picture_alt</md-icon>

          <div class="md-list-item-text">
            <span>458.560.208-98</span>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>Data de nascimento</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">event</md-icon>

          <div class="md-list-item-text">
            <span>16/05/1998</span>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>Telefone</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">local_phone</md-icon>

          <div class="md-list-item-text">
            <span>(11) 97590-5803</span>
            <span>Movel</span>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>Senha</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">fingerprint</md-icon>

          <div class="md-list-item-text">
            <span>123456</span>
          </div>
        </md-list-item>

      </md-list>
    </div>
  </div>
</template>

<script>
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
    OrderedTable
  },
  data () {
    return {
      dailySalesChart: {
        data: {
          labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S'],
          series: [
            [12, 17, 7, 17, 23, 18, 38]
          ]
        },
        options: {
          lineSmooth: this.$Chartist.Interpolation.cardinal({
            tension: 0
          }),
          low: 0,
          high: 50, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
        }
      },
      dataCompletedTasksChart: {
        data: {
          labels: ['12am', '3pm', '6pm', '9pm', '12pm', '3am', '6am', '9am'],
          series: [
            [230, 750, 450, 300, 280, 240, 200, 190]
          ]
        },

        options: {
          lineSmooth: this.$Chartist.Interpolation.cardinal({
            tension: 0
          }),
          low: 0,
          high: 1000, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
        }
      },
      emailsSubscriptionChart: {
        data: {
          labels: ['Ja', 'Fe', 'Ma', 'Ap', 'Mai', 'Ju', 'Jul', 'Au', 'Se', 'Oc', 'No', 'De'],
          series: [
            [542, 443, 320, 780, 553, 453, 326, 434, 568, 610, 756, 895]

          ]
        },
        options: {
          axisX: {
            showGrid: false
          },
          low: 0,
          high: 1000,
          chartPadding: {
            top: 0,
            right: 5,
            bottom: 0,
            left: 0
          }
        },
        responsiveOptions: [
          ['screen and (max-width: 640px)', {
            seriesBarDistance: 5,
            axisX: {
              labelInterpolationFnc: function (value) {
                return value[0]
              }
            }
          }]
        ]
      }
    }
  }
}
</script>
