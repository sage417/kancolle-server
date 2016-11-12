cd @project.basedir@/target
rm -rf build
mkdir build
cp ./@project.build.finalName@.@project.packaging@ ./build
cp ./classes/Dockerfile ./build
docker build -t sage417/kancolle-server:v@project.version@ ./build