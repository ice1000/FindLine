package utils.exceptions

import core.models.Point

/**
 * @author ice1000
 * Created by ice1000 on 2016/8/8.
 */
class LineNotFoundException(point: Point) : Exception("no line found at p" + point.toString()) {

	constructor(x: Int, y: Int) : this(Point(x, y))

}