def sonarChecks() {
    sh '''
        echo Sonar Checks In Progress
        sonar-scanner -Dsonar.projectKey=${COMPONENT} ${ARGS} -Dsonar.login=c83e5f2df3ea9ac79a5029df5f37d7177535df6a -Dsonar.host.url=http://172.31.90.194:9000
        curl https://gitlab.com/thecloudcareers/opensource/-/raw/master/lab-tools/sonar-scanner/quality-gate > sonar-quality-gate.sh
        sonar-quality-gate.sh ${SONARCRED_USR} ${SONARCRED_PSW} ${SONARURL} ${COMPONENT}
        echo Sonar Checks Completed
    '''
}