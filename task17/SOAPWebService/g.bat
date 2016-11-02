
rem wsgen -verbose -keep -cp ./bin  com.epam.jmp.dr.task17.soap.ws.SOAPWebServiceImpl
cd ./src
wsgen -verbose -cp ../bin -Xnocompile com.epam.jmp.dr.task17.soap.ws.SOAPWebServiceImpl
cd ..