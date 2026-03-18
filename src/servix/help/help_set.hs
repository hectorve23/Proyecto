<?xml version="1.0" encoding="UTF-8"?>
<helpset version="1.0">
    <title>Ayuda de Servix - Gestión de Restaurantes</title>
    <maps>
        <homeID>main</homeID>
        <mapref location="map_file.jhm"/>
    </maps>

    <view>
        <name>Tabla de contenidos</name>
        <label>Contenidos</label>
        <type>javax.help.TOCView</type>
        <data>toc.xml</data>
    </view>

    <view>
        <name>Indice</name>
        <label>Índice Alfabético</label>
        <type>javax.help.IndexView</type>
        <data>indice.xml</data>
    </view>

    <view>
        <name>Buscar</name>
        <label>Buscador</label>
        <type>javax.help.SearchView</type>
        <data engine="com.sun.java.help.search.DefaultSearchEngine">
            JavaHelpSearch
        </data>
    </view>
</helpset>

