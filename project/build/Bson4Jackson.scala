import sbt._
import com.weiglewilczek.bnd4sbt.BNDPlugin

class Bson4JacksonProject(info: ProjectInfo) extends DefaultProject(info) with BNDPlugin {
  val jacksonRepo = Resolver.url("Jackson Maven Repository",
    new java.net.URL("http://snapshots.repository.codehaus.org/"))(Patterns(
    "[organisation]/[module]/[revision]-SNAPSHOT/[artifact]-[revision](-[timestamp]).[ext]"))
  val jackson = "org.codehaus.jackson" % "jackson-core-asl" % "1.7.0" extra ("timestamp" -> "20110105.013252-7")
  val jacksonMapper = "org.codehaus.jackson" % "jackson-mapper-asl" % "1.7.0" extra ("timestamp" -> "20110105.013252-7")
  
  val junit = "junit" % "junit" % "4.8.1" % "test"
  val junitInterface = "com.novocode" % "junit-interface" % "0.4" % "test"
  val mongodb = "org.mongodb" % "mongo-java-driver" % "2.1" % "test"
  
  //omit scala version
  override def outputPath = "target"
  override def moduleID = "bson4jackson"
  
  //change names of source artifacts
  override def packageSrcJar = defaultJarPath("-sources.jar")
  override def packageTestSrcJar = defaultJarPath("-test-sources.jar")
  
  //configure OSGi bundle
  override def bndBundleName = "bson4jackson"
  override def bndBundleVendor = Some("Michel Kraemer")
  override def bndExportPackage = Seq(
    "de.undercouch.bson4jackson",
    "de.undercouch.bson4jackson.io",
    "de.undercouch.bson4jackson.types"
  ).map(_ + ";version=" + projectVersion.value)
  override def bndPrivatePackage = Seq()
  override def bndIncludeResource = Seq()
}
