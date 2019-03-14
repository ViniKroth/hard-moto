# hard-moto – Tech test

# Problematic
“Petter needs to deliver a lot of packages during the night. The night is long and cold.
Petter just want to get home and watch some Netflix TV Shows. We need help
Petter to achieve his goals as fast and as more reliable as possible. IF Petter chooses
the slow route we would make Petter lose that killer episode, not to mention that he
will be a easy pray for the spoilers. We need build a system(UI is not required but feel free to use Jenkins) in
order to let Petter: Store city information: name, ID, distance. Later on we need to
be able to search for the shortest route from one city to another. The operations must be all online and tail latency should
be small as possible like less than 2 seconds each request..”

# Requisites
- Use diferente machines for Jenkins, Microservice, Redis and Cassandra. 
- Every component should have a dedicated machine.
- Don't use DOCKER.
- Don't use Spring Boot.
- You can use Any Centralized Log / Metrics solution you want - this will be a HUGE plus for you. - You need to have multiple jobs in jenkins.
- You need to have a dedicated MACHINE for Jenkins.
- Don't deploy Microservice in same box as Jenkins. 

# Defined strategy and how I did it
Study first. Do it later.
With the help of the devops-bootcamp-express course, I first studied all the necessary technologies, enhancing my knowledge on a few (eg: Packer, Jenkins), and getting a first look into some unknown grounds (eg: Terraform, AWS).
Afterwards, it was time for the development. Unfortunately, I was not able to accomplish all the required features, mostly in the DevOps area, but even tough a lot was learned, and the motivation of using these technologies in different projects (professional and personal), has been developed.
So, in a nutshell, I would like to have had more time to deliver the best product as possible, but I have deeply enjoyed the experience and hope to have the possibility of working with those technologies once again soon.

# Accomplished features:
- Microsservice structure
- All endpoints
- Cassandra
- Running on Jetty (Standalone)
- AWS (Partially)
- Packer (Partially)

# Room for Improvement:
- Packer -> Complete using
- AWS -> Complete using
- Jenkins -> Use
- Ansible Use
- Terraform -> Use
- Redis-> Use

# How to run
Even tough i was able to bake some AMI's using packer, the App can only run locally (with Cassandra on same host).
To run, the process is quite simple, all you need to do is clean build (using gradlew) a new .war file, and deploy it on you favorite servlet. ( Here’s a link on to install and deploy an app on Jetty → https://gist.github.com/ViniFKroth/ad76e8a1b2157f88751a918be55e458f).
