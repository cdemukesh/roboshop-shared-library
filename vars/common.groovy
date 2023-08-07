def sonarChecks() {
    sh '''
        echo Sonar Checks In Progress
        sonar-scanner -Dsonar.projectKey=${COMPONENT} ${ARGS} -Dsonar.login=${SONARCRED_USR} -Dsonar.password=${SONARCRED_PSW} -Dsonar.host.url=http://${SONARURL}:9000
        curl https://gitlab.com/thecloudcareers/opensource/-/raw/master/lab-tools/sonar-scanner/quality-gate > sonar-quality-gate.sh
        sonar-quality-gate.sh ${SONARCRED_USR} ${SONARCRED_PSW} ${SONARURL} ${COMPONENT}
        echo Sonar Checks Completed
    '''
}