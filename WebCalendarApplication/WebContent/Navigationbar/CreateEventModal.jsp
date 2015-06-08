<!---------------------------
	Modal dialog for event creation.
	Created by Alessandro.
----------------------------->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<li>
	<button class="btn btn-default navbar-btn" data-toggle="modal" data-target="#myModal">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
	</button>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Neuer Termin</h4>
				</div>
				<div class="modal-body">
					<div id="multidivcontainer">
						<div class="title">
							<label for="title" class="control-label">Titel</label>
							<input type="text" class="form-control " name="title" placeholder="Titel" required="required"/>
						</div>
						<div class="place">
							<label for="ort" class="control-label">Ort</label>
							<input type="text" class="form-control " name="ort" placeholder="Ort" required="required"/>
						</div>
					</div>
					<label for="begindate" class="control-label">Startzeitpunkt</label>
					<div id="multidivcontainer">
						<div class="input-group date begindate" id="datetimepicker2">
							<input readonly type="text" class="form-control"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						<div class="starttime input-group clockpicker" data-autoclose="true">
							<input readonly type="text" class="form-control" value="12:00"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-time"></span>
							</span>
						</div>
					</div>
					<label for="begindate" class="control-label">Endzeitpunkt</label>
					<div id="multidivcontainer" class="form-group">
						<div class="input-group date begindate" id="datetimepicker1">
							<input readonly type="text" class="form-control"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						<div class="starttime input-group clockpicker" data-autoclose="true">
							<input readonly type="text" class="form-control" value="13:00"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-time"></span>
							</span>
						</div>
					</div>
					<label for="kategorie" class="control-label">Kategorie</label>
					<div id="multidivcontainer">
						<select class="selectpicker selec" title='Kategorie auswählen'>
							<option disabled="disabled" selected="selected" data-icon="glyphicon glyphicon-tags">&nbsp;Kategorie auswählen</option>
							<option data-icon="glyphicon glyphicon-home">Privat</option>
							<option data-icon="glyphicon glyphicon-briefcase">Geschfätlich</option>
							<option data-icon="glyphicon glyphicon-bookmark">Hobby</option>
							<option data-icon="glyphicon glyphicon-gift">Geburtstag</option>
							<option data-icon="glyphicon glyphicon-flag">Feiertag</option>
							<option data-icon="glyphicon glyphicon-plane">Urlaub</option>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Schließen</button>
					<button type="button" type="submit" class="btn btn-primary">Termin erstellen</button>
				</div>
			</div>
		</div>
	</div>
</li>