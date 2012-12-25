cd /Users/allwefantasy/CSDNWorkSpace/framework/out/production/CSDNMongoMongo/
jar cvf mongomongo.jar net
cp mongomongo.jar /Users/allwefantasy/CSDNWorkSpace/mylib
cd /Users/allwefantasy/CSDNWorkSpace/framework/out/production/CSDNCommon/
jar cvf csdn-common.jar net
cp csdn-common.jar /Users/allwefantasy/CSDNWorkSpace/mylib
cd /Users/allwefantasy/CSDNWorkSpace/framework/out/production/CSDNORM/
jar cvf active-orm.jar net
cp active-orm.jar /Users/allwefantasy/CSDNWorkSpace/mylib
cp /Users/allwefantasy/CSDNWorkSpace/mylib/* /Users/allwefantasy/CSDNWorkSpace/framework/lib

cd /Users/allwefantasy/CSDNWorkSpace/framework/out/production/Framework/
jar cvf service_framework.jar net
cp  service_framework.jar /Users/allwefantasy/CSDNWorkSpace/framework/jar
cp  service_framework.jar /Users/allwefantasy/CSDNWorkSpace/PushService/lib

cp ~/CSDNWorkSpace/mylib/* /Users/allwefantasy/CSDNWorkSpace/framework/lib
