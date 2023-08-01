def lintChecks() {
    sh '''
        echo Installing AngularLint for ${COMPONENT}
        echo Lint Check Completed for ${COMPONENT}
    '''
}
def call() {
    pipeline {
        agent { label 'WS' }
        stages {
            stage('Lint Checks') {                                          // Start of the stages
                steps {
                    script {
                        lintChecks()
                    }
                }
            }                                                                
            stage('Code Quality Analysis') {
                steps {
                    sh "echo Code Quality Analysis Is In Place."
                }
            }
        }                                                                   // End of the stages
    }
}




            //    script { 
            //         sample.info('CATALOGUE')
            //     }