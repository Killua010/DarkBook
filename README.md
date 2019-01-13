# DarkBook
<h3>software de vendas online de uma livraria</h3>
<a href="https://thedarkbook-c57e8.firebaseapp.com/#/">URL da aplicação</a>
<h2>Projeto vue</h2>
<h4>Rodar o Projeto: </h4>
<ul>
  <li> Para rodar o projeto em vue é necessario ter o :
    <h3>Dependências:</h3>
    <ul><li><a href="https://nodejs.org/en/">Node instalado</a>.</li></ul>
  </li>
  <h3>Rodar o projeto:</h3>
  <ul>
    <li> - dentro da pasta front execute o comando <em>npm install</em>. Após rodar o código, as dependecias da aplicação serão baixadas.</li>
    <li> - Por fim execute o comando <em>npm run dev</em>. Para subir um servidor local da aplicação.</li>
  </ul>
</ul>
<h2>Projeto Java</h2>
<ul>
  <li> Para rodar o projeto Java é necessario ter o : 
    <h3>Dependências:</h3>
    <ul>
      <li><a href="https://www.oracle.com/technetwork/java/javaee/overview/index.html">Java EE</a></li>
      <li><a href="http://tomcat.apache.org/">Apache Tomcat</a>.</li>
    </ul>
    <h3>Tutoriais:</h3>
    <ul>
      <li><em><a href="https://www.caelum.com.br/apostila-java-web/o-que-e-java-ee/#exerccios-preparando-o-tomcat">Tutorial configurando apache no eclipse</a></em></li>
    </ul>
    <h3>Rodar o projeto no eclipse:</h3>
    <ul>
      <li>Vá na aba File > import > Maven > Existing Maven Project > Selecione o caminho da aplicação "backend/DarkBook" </li>
      <li>Clique no projeto com o botão direito do mouse > Maven > Update Project > Selecione Force Update of Snapshot/Releases </li>
      <li>Clique no projeto > vá para a aba Run > Run As > Run on Server > selecione o seu servidor de aplicação > Adicione o projeto ao seu servidor</li>
      <li>PS: A aplicação VUE utiliza a porta 8082 para fazer as requisições ao servidor</li>
      <ul>
        <li>Para alterar a porta do servidor apache Tomcat:</li>
        <li>Vá na aba Window > Show View > Other > Server</li>
        <li>Na nova aba Server > clique duplo no servidor Tomcat > no canto direito na aba Ports > clique duplo na Port Number do HTTP e troque para 8082</li>
      </ul>
    </ul>
  </li>
</ul>
