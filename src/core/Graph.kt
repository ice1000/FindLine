package core

import core.models.Line
import core.models.Point
import core.processors.BinaryProcessor
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * @author ice1000
 * Created by ice1000 on 16-8-6.
 */
class Graph(file: File) {
	val image: BufferedImage
	val cache: BufferedImage
	val mark: Array<Array<Boolean>>

	init {
		image = ImageIO.read(file)
		cache = ImageIO.read(file)
		mark = Array(cache.width, {
			Array(cache.height, { false })
		})
		init()
	}

	fun init() {
		(0..image.width - 1).forEach { x ->
			(0..image.height - 1).forEach { y ->
				image.setRGB(x, y, color(x, y))
				mark[x][y] = this[x, y]
			}
		}

	}

	/**
	 * @param x x in image
	 * @param y y in image
	 * @return black or white
	 */
	private fun color(x: Int, y: Int) =
			if (get(x, y))
				Color.WHITE.rgb
			else
				Color.BLACK.rgb

	private fun color(point: Point) =
			color(point.x, point.y)

	operator fun get(x: Int, y: Int) =
			BinaryProcessor.getGray(cache.getRGB(x, y)) > average

	operator fun get(point: Point) = this[point.x, point.y]

	data class Max(val x: Int, val y: Int, val length: Double)

	/** 判断两点是否连通 */
	operator fun Point.rangeTo(point: Point): Boolean {
		val line = Line.fromPoint(this, point)
		(Math.min(this.x, point.x)..Math.max(this.x, point.x)).forEach { x ->
			if (!line[x.toDouble(), line.longitude(x)])
				return false
		}
		(Math.min(this.y, point.y)..Math.max(this.y, point.y)).forEach { y ->
			if (!line[y.toDouble(), line.latitude(y)])
				return false
		}
		return true
	}

}