import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class FileUtils {
    static private Logger logger = Logging.getLogger(FileUtils.class)

    static def deleteDir(File f) {
        if (!f.exists())
            return
        logger.info("Deleting " + f.absolutePath)
        if (!f.deleteDir())
            throw new RuntimeException("Unable to delete " + f.absolutePath)
    }

    static def cleanDir(File f) {
        deleteDir(f)
        f.mkdirs()
    }
}
