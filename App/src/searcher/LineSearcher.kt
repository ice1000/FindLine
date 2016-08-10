package searcher

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * @author ice1000
 * Created by ice1000 on 2016/8/10.
 */
open class LineSearcher(image: BufferedImage) : Searcher666(image) {
	override fun createFromFile(file: File) = LineSearcher(ImageIO.read(file)!!)

	override fun search() {

	}

}