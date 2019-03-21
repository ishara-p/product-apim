# ------------------------------------------------------------------------

set -e

ECHO=`which echo`
KUBECTL=`which kubectl`

# methods
function echoBold () {
    ${ECHO} -e $'\e[1m'"${1}"$'\e[0m'
}

function usage () {
    echoBold "This script automates the backend service resources\n"
    echoBold "Allowed arguments:\n"
    echoBold "-h | --help"
    echoBold "--u | --username\t\t username"
    echoBold "--cap | --cluster-admin-password\tKubernetes cluster admin password\n\n"
}

USERNAME=''
ADMIN_PASSWORD=''

# capture named arguments
while [ "$1" != "" ]; do
    PARAM=`echo $1 | awk -F= '{print $1}'`
    VALUE=`echo $1 | awk -F= '{print $2}'`

    case ${PARAM} in
        -h | --help)
            usage
            exit 1

            ;;
        --u | --username)
            USERNAME=${VALUE}
            ;;
        --cap | --cluster-admin-password)
            ADMIN_PASSWORD=${VALUE}
            ;;
        *)
            echoBold "ERROR: unknown parameter \"${PARAM}\""
            usage
            exit 1
            ;;
    esac
    shift
done
echoBold 'Deploying BACK END SERVICE...'
#update <PATH_FOR_THE_SERVICE_DEPLOYMENT_YML_FILE> with your backend service yml file
${KUBECTL} delete -f ../service/kubernetes/vehicle_rest_service/vehicle_rest_service_svc.yaml -n wso2
#update <PATH_FOR_THE_SERVICE_DEPLOYMENT_YML_FILE> with your backend service deployment yml file
${KUBECTL} delete -f ../service/kubernetes/vehicle_rest_service/vehicle_rest_service_deployment.yaml -n wso2
sleep 10s
echoBold 'Finished'
