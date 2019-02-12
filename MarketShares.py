# Rhodes.355

# Imports
import arcpy

# Get and set parameters
in_features_point = arcpy.GetParameterAsText(0) # Point feature
in_features_polygon = arcpy.GetParameterAsText(1) # Polygon Feature
in_field_join_features = arcpy.GetParameterAsText(2) # Polygon feature field
in_field_oldarea = arcpy.GetParameterAsText(3) # Area of polygon
in_table_population = arcpy.GetParameterAsText(4) # Population table
in_field_join_table = arcpy.GetParameterAsText(5) # Population table join field
in_field_pop = arcpy.GetParameterAsText(6) # Population field
out_features = arcpy.GetParameterAsText(7) # Output

desc = arcpy.Describe(in_features_polygon)
arcpy.env.extent = desc.extent

arcpy.env.overwriteOutput = True
# arcpy.AddMessage()

# Calculate total population
cursor = arcpy.da.SearchCursor(in_table_population, [in_field_pop])
total_pop = 0
cursor.reset()
for row in cursor:
    total_pop += row[0]

# Create Thiessen using library locations use same extent as in_features polygon
thiessen = arcpy.env.scratchGDB + '/thiessen1'
arcpy.CreateThiessenPolygons_analysis(in_features_point, thiessen, "ALL")

# Intersect Thiessen with in_polygon
intersect = arcpy.env.scratchGDB + '/intersect1'
arcpy.Intersect_analysis([thiessen, in_features_polygon], intersect)

# Join population table to intersected polygons using common field
arcpy.JoinField_management(intersect,in_field_join_features,in_table_population, in_field_join_table)

# Calculate new field by multiplying population with the ratio of area after and before intersection
arcpy.AddField_management(intersect, "POP", "DOUBLE")

cursor = arcpy.da.UpdateCursor(intersect,[in_field_oldarea, 'Shape_Area', in_field_pop, 'POP'])
cursor.reset()
for row in cursor:
    #arcpy.AddMessage("{0}//{1}*{2}".format(row[1],row[0],row[2]))
    pop = (row[1]/row[0])*row[2]
    row[3] = pop
    cursor.updateRow(row)

# Calculate percent of population served
arcpy.AddField_management(intersect, "Percentage", "DOUBLE")

cursor = arcpy.da.UpdateCursor(intersect,['POP', 'Percentage'])
cursor.reset()
for row in cursor:
    #arcpy.AddMessage("100 * {0}/{1}".format(row[0],row[1]))
    pcnt = 100 * row[0]/total_pop
    row[1] = pcnt
    cursor.updateRow(row)

# Dissolve the layer from the last step using library names need to specify SUM of the new field as statistic field
arcpy.Dissolve_management(intersect, out_features, 'NAME', 'POP SUM', "MULTI_PART", "DISSOLVE_LINES")

arcpy.AddField_management(out_features, "Percentage", "DOUBLE")

cursor = arcpy.da.UpdateCursor(out_features,['SUM_POP', 'Percentage'])
cursor.reset()
for row in cursor:
    #arcpy.AddMessage("100 * {0}/{1}".format(row[0],row[1]))
    pcnt = 100 * (row[0]/total_pop)
    row[1] = pcnt
    cursor.updateRow(row)