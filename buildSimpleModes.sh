#!/bin/sh
localPath=`pwd`;
projectName=$1;
packageName=$2;
parentName=$projectName"-parent";
webName=$projectName"-web";
testName=$projectName"-test";

commonName=$projectName"-common-parent";
commonDomainName=$projectName"-common-domain";
commonUtilName=$projectName"-common-util";

serviceName=$projectName"-service-parent";
serviceCoreName=$projectName"-service-core";
serviceUtilName=$projectName"-service-util";
serviceFacadeName=$projectName"-service-facade";

coreName=$projectName"-core-parent";
coreUtilName=$projectName"-core-util";
coreDalName=$projectName"-core-dal";
coreDalApiName=$projectName"-core-dal-api";
coreDalMysqlName=$projectName"-core-dal-mysql";


# parentName
mvn archetype:generate -DgroupId="$packageName" -DartifactId="$parentName" -DarchetypeArtifactId=maven-archetype-site-simple -DinteractiveMode=false -DarchetypeCatalog=local;
cd $localPath"/$parentName";rm -rf src;

# webName
mvn archetype:generate -DgroupId="$packageName".web -DartifactId="$webName" -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false -X -DarchetypeCatalog=local;

# testName
mvn archetype:generate -DgroupId="$packageName".test -DartifactId="$testName" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -X -DarchetypeCatalog=local;

# commonName,serviceName,coreName
mvn archetype:generate -DgroupId="$packageName".common -DartifactId="$commonName" -DarchetypeArtifactId=maven-archetype-site-simple -DinteractiveMode=false -X -DarchetypeCatalog=local;
mvn archetype:generate -DgroupId="$packageName".service -DartifactId="$serviceName" -DarchetypeArtifactId=maven-archetype-site-simple -DinteractiveMode=false -X -DarchetypeCatalog=local;
mvn archetype:generate -DgroupId="$packageName".core -DartifactId="$coreName" -DarchetypeArtifactId=maven-archetype-site-simple -DinteractiveMode=false -X -DarchetypeCatalog=local;

# commonDomainName,commonUtilName
cd $localPath"/$parentName/"$commonName;rm -rf src;
mvn archetype:generate -DgroupId="$packageName".common -DartifactId="$commonDomainName" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -X -DarchetypeCatalog=local;
mvn archetype:generate -DgroupId="$packageName".common -DartifactId="$commonUtilName" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -X -DarchetypeCatalog=local;

# serviceCoreName,serviceUtilName,serviceFacadeName
cd $localPath"/$parentName/"$serviceName;rm -rf src;
mvn archetype:generate -DgroupId="$packageName".service -DartifactId="$serviceCoreName" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -X -DarchetypeCatalog=local;
mvn archetype:generate -DgroupId="$packageName".service -DartifactId="$serviceUtilName" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -X -DarchetypeCatalog=local;
mvn archetype:generate -DgroupId="$packageName".service -DartifactId="$serviceFacadeName" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -X -DarchetypeCatalog=local;

# coreUtilName,coreDalName
cd $localPath"/$parentName/"$coreName;rm -rf src;
mvn archetype:generate -DgroupId="$packageName".core -DartifactId="$coreUtilName" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -X -DarchetypeCatalog=local;

# coreDalName
mvn archetype:generate -DgroupId="$packageName".core.dal -DartifactId="$coreDalName" -DarchetypeArtifactId=maven-archetype-site-simple -DinteractiveMode=false -X -DarchetypeCatalog=local;

# coreDalApiName,coreDalMysqlName
cd $localPath"/$parentName/"$coreName"/$coreDalName";rm -rf src;
mvn archetype:generate -DgroupId="$packageName".core.dal -DartifactId="$coreDalApiName" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -X -DarchetypeCatalog=local;
mvn archetype:generate -DgroupId="$packageName".core.dal -DartifactId="$coreDalMysqlName" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -X -DarchetypeCatalog=local;




