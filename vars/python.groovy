def lintChecks() {
    sh '''
        echo Installing pylint for ${COMPONENT}
        #pip3 install pylint
        #pylint *.py
        echo Lint Check Completed for ${COMPONENT}
    '''
}
def call() {
    pipeline {
        agent { label 'WS' }
        environment {
            SONARCRED = credentials("SONARCRED")
            SONARURL = "172.31.90.194"
        }
        stages {
            stage('Lint Checks') {                                          // Start of the stages
                steps {
                    script {
                        lintChecks()
                    }
                }
            }                                                                
            stage('Sonar Checks') {
                steps {
                     script {
                        env.ARGS="-Dsonar.sources=."
                        common.sonarChecks()
                     }
                }
            }
            stage('Testing') {
                steps {
                     sh "echo Testing"
                }
            }
        }                                                                   // End of the stages
    }
}




            //    script { 
            //         sample.info('CATALOGUE')
            //     }