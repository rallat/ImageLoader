from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice

device = MonkeyRunner.waitForConnection()
device.installPackage("../imageLoaderTester/target/imageloadertester.apk")
package = 'com.novoda.lib.imageloadertester'
activity = 'com.novoda.lib.imageloadertester.ImageLoaderTestActivity'
runComponent = package + '/' + activity
device.startActivity(component=runComponent)


result = device.takeSnapshot()
result.writeToFile('target/result/shot1.png','png')
