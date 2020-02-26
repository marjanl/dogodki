# vnos dogodkov za Igorja

hse.db je file z sqlite bazo. Fino bi bilo to dnevno/tedensko backapirat!

Navodila po korakih:
1. instaliraj wildfly
2. instaliraj maven 
3. dobiš zip projekta, ga odpakiraš in z njem zalaufaš
4. mvn clean package  --> v target je dogodki.war, to je aplikacija!
5. hse.db daš nekam kje ga vidi wildfly.
6. skopiraš(mogoče najboljš samo datasource in ip/any) /additional/standalone.xml v /wildfly/standalone/configuration/standalone.xml
7. skopiraš mapo /additinal/main /wildfly/modules/system/layers/base/org/eclipse/persistence/ ! To je za eclipseLink
8. skopiraš https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.25.2/sqlite-jdbc-3.25.2.jar v /wildfly/standalone/deployments/
9. skopiraš dogodki.war v /wildfly/standalone/deployments/
10. zalaufaš wildfly...odpreš http://localhost:8080/dogodki/dashboard.xhtml ali http://localhost:8080/dogodki/
11. greš na kavo :)


ps.
nastavitve standalone.xml za data source in da posluša na vseh interfacih
na serverju je ta file v /wildfly/standalone/configuration/
mogoče je kle potrebno popravit pot do hse.db (sqlite) fajla
poglej si datasource pravi
<datasource jndi-name="java:/hseDS" pool-name="SqlitePool">
    <connection-url>jdbc:sqlite:/home/ml/kastor/work/git/Igor/hse.db</connection-url>
    <driver>sqlite</driver>
</datasource>