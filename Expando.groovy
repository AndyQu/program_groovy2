def ford=new Expando(tell:{->println "I am ${brand}"})
ford.brand="ford"
ford.year=2015
println "ford:${ford}"
ford.tell()

def volvo=new Expando(brand:"volvo", year:2016)
volvo.tell={
    ->println "Ladies and Gentlemen, this is ${brand}"
}
println "volvo:${volvo}"
volvo.tell()