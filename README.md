```sh
git clone https://bitbucket.org/rstm-sf/qsimulator.git
cd qsimulator
git submodule update --init --recursive

cd src/main/cpp/quantum-simulator/
mkdir build && cd build
cmake ..
cmake --build .

cd ../../../../..
mvn clean install
```
