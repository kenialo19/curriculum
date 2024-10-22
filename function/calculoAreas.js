function areaTriangulo(base, altura) {
    let area = (base / 2) * altura;
    console.log(`Área triángulo: ${area}`);
}
areaTriangulo(8, 2);

function areaCirculo(radio) {
    let pi = Math.PI;
    let area = pi * (radio * radio);
    console.log(`Área círculo: ${area}`);
}
areaCirculo(16);

//ejecutar comando en cosola -> node calculoAreas.js