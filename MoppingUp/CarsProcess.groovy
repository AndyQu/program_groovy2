def lines=new File("cars.csv").readLines()
def columns=lines[0].split(",")
println "columns:${columns}"
lines-=lines[0]

def avgMiles={
    ->
    delegate.miles.toLong()/(2008-delegate.year.toLong())
}

def cars=lines.collect{
    String line->
        def car=new Expando()
        line.split(",").eachWithIndex{
            it,index->
                car."${columns[index].trim()}"=it
        }
        car.getAvgMiles=avgMiles
        car
}
cars.each{println "$it ${it.getAvgMiles()}"}
