import { Component, Input, OnInit } from '@angular/core';
import * as Backbone from 'backbone';
import * as joint from 'jointjs';
import { Projectworkflow } from 'src/app/core/models/projectworkflow';
/// <reference types="backbone" />

@Component({
  selector: 'app-flowchart',
  template: `<div id="mychart"></div>`,
  styleUrls: ['./flowchart.component.scss']
})
export class FlowchartComponent implements OnInit {



    @Input() data: any;

  constructor() { }

  ngOnInit(): void {
    const graph = new joint.dia.Graph();
    const paper = new joint.dia.Paper({
      el: document.getElementById('mychart'),
      model: graph,
      width: 1500,
      height: 600,
      gridSize: 10,
    });

    const shapeWidth = 300; // Width of each shape
    const shapeHeight = 50; // Height of each shape
    const centerX = (800 / 2 )- (shapeWidth / 2); // X-coordinate for center of the paper

    const shapes = [];
    //start node
    const shape = new joint.shapes.standard.Rectangle();
    shape.position(centerX, 200 + (-1 * (shapeHeight + 20)));
    shape.resize(shapeWidth, shapeHeight);
    shape.attr({
      body: {
        fill: 'green',
        rx: 10,
        ry: 10,
      },
      label: {
        text: "Start",
        fill: 'white',
        fontSize: 14,
      },
    });
    shapes.push(shape);
    graph.addCell(shape);
  for (let i = 0; i < this.data.tasks.length; i++) {
    const node = this.data.tasks[i];
    const shape = new joint.shapes.standard.Rectangle();
    shape.position(centerX, 200 + i * (shapeHeight + 20));
    shape.resize(shapeWidth, shapeHeight);
    shape.attr({
      body: {
        fill: 'blue',
        rx: 10,
        ry: 10,
      },
      label: {
        text: "Task "+(i+1)+" : "+node.name+" ("+node.status+")",
        fill: 'white',
        fontSize: 14,
      },
    });
    shapes.push(shape);
    graph.addCell(shape);
  }
//finish node
   const shape1 = new joint.shapes.standard.Rectangle();
   shape1.position(centerX, 200 + (this.data.tasks.length) * (shapeHeight + 20));
   shape1.resize(shapeWidth, shapeHeight);
   shape1.attr({
      body: {
        fill: 'red',
        rx: 10,
        ry: 10,
      },
      label: {
        text: "End",
        fill: 'white',
        fontSize: 14,
      },
    });
    shapes.push(shape1);
    graph.addCell(shape1);

  // Create links
  for (let i = 0; i < shapes.length - 1; i++) {
    const sourceShape = shapes[i];
    const targetShape = shapes[i + 1];
    const link = new joint.shapes.standard.Link({
      source: {
        id: sourceShape.id,
        anchor: {
          name: 'center',
        },
      },
      target: {
        id: targetShape.id,
        anchor: {
          name: 'center',
        },
      },
    });
    graph.addCell(link);
  }
}
}
