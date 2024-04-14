pipeline{
  agent any
tools {
        maven "Maven"
    }

  stages{
        stage('Build'){
            steps{
                bat 'mvn -Dmaven.test.skip=true clean install'
            }
        }
        stage('Test Service'){
              steps{
                  bat 'mvn test -Dtest=AssociationServiceImplTest'
                  bat 'mvn test -Dtest=BiensEssentielServiceImplTest'
                  bat 'mvn test -Dtest=UtilisateurServiceImplTest'
                  bat 'mvn test -Dtest=DowarServiceImplTest'
                  bat 'mvn test -Dtest=KafilaServiceImplTest'
                  bat 'mvn test -Dtest=MemberServiceImplTest'
                  bat 'mvn test -Dtest=VilleServiceImplTest'

              }
        }
        stage('Test Controller'){
                      steps{
                         bat 'mvn test -Dtest=AssociationControllerImplTest'
                         bat 'mvn test -Dtest=BiensEssentielControllerImplTest'
                         bat 'mvn test -Dtest=UtilisateurControllerImplTest'
                         bat 'mvn test -Dtest=DowarControllerImplTest'
                         bat 'mvn test -Dtest=KafilaControllerImplTest'
                         bat 'mvn test -Dtest=MemberControllerImplTest'
                         bat 'mvn test -Dtest=VilleControllerImplTest'
                      }
        }
  }
}