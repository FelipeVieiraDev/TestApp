<h1 align="center">TestApp</h1>


<h2 ">Descrição do Projeto</h2>
<p>Aplicação Android desenvolvida utilizando Kotlin e arquitetura MVVM, com as finalidades de cadastrar e listar nomes e sobrenomes utilizando room, exibir números primos a cada 3 segundos utilizando threads e exemplificar o uso do BroadcastReceiver</p>

<h2 ">Como executar</h2>
<p>1. Instale o Android Studio</p>
<p>2. Através do terminal, clone o repositório com o seguinte comando:
$ git clone https://github.com/FelipeVieiraDev/TestApp</p>
<p>3. Abre o projeto utilizando o Android Studio e aguarde a indexação</p>
<p>4. Execute o projeto</p>


<h2>Utilizações</h2>

Os componentes, bibliotescas e técnicas utilizadas foram pensadas para dar a aplicação fluidez, organização e escalabilidade.

<b>MVVM</b>

Projeto divido em 3 camadas:
A camada de Model, onde estão presentes a origem dos dados apresentados na aplicação. (Por exemplo classes de banco de dados).
A camada de View, onde estão presentes as activitys/fragments responsaveís por receber e apresentar os dados.
E a camada ViewModel, que vai abstrair a view e ser responsável pelo tratamento das informações apresentadas.

<b>Material Design</b>

As interfaces foram desenvolvidas utilizando componentes do Material Desing, para melhorar a experiência do usuário.

<b>Coroutines</b>

Courotines foram utilizadas para conseguir fazer um bom uso das threads disponível e potencializar a performance da aplicação, sendo possível executar rotinas em cooperação.

<b>Koin</b>

Biblioteca utilizada para injeção de dependências por ser simples e pratica, além de funcionar bem.

<b>Android Jetpack Nabigation</b>

Componente utilizada para facilitar a navegação entre as telas.

<h2>Detalhes</h2>

Os objetivos eram: 

<p>Criar um app simples de cadastro e listagem onde o usuário deverá informar Nome e Sobrenome
os dados deverão ser inseridos e persistidos no banco de dados SQLite utilizando ORMLite ou
Room. Não é necessário se preocupar com qualidade do layout</p>

R: Na primeira aba do aplicativo, "Cadastro de Nomes", são apresentados campos para preencher nome e sobrenome, um botão para salvar essas informações e outro botão para ir diretamente para listagem de nomes.
A listagem de nomes fica localizada na segunda aba do App e pode ser acessada através da bottomNavigationBar

<p>Criar um app simples utilizando Thread que imprima um número primo a cada 3 segundos  </p>

R: A terceita aba do App, "Números primos", imprime um número primo a cada 3 segundos, o limite estabelicido manualmente via código é de números primos menores que 100, mas o código vai funcionar para qualquer limite.

<p>Criar um exemplo utilizando comunicação através de BroadcastReceiver </p>

R: A activity principal do App está configurada com um Observer no BroadcastReceiver para quando o usuário receber uma ligação do tipo "android.intent.action.PHONE_STATE", o App apresentar um toast notification.
