package com.ocpsoft.socialpm.gwt.client.local.view;

import javax.enterprise.context.ApplicationScoped;

import com.ocpsoft.socialpm.gwt.client.local.view.component.BreadCrumb;
import com.ocpsoft.socialpm.gwt.client.local.view.component.BreadCrumbList;
import com.ocpsoft.socialpm.gwt.client.local.view.component.Div;
import com.ocpsoft.socialpm.gwt.client.local.view.component.Heading;
import com.ocpsoft.socialpm.gwt.client.local.view.component.ProfileLink;
import com.ocpsoft.socialpm.gwt.client.local.view.component.ProjectLink;
import com.ocpsoft.socialpm.gwt.client.local.view.component.Span;
import com.ocpsoft.socialpm.gwt.client.local.view.component.StoryLink;
import com.ocpsoft.socialpm.model.project.Project;
import com.ocpsoft.socialpm.model.project.story.Story;

@ApplicationScoped
public class StoryViewImpl extends FixedLayoutView implements StoryView
{
   private Project project;

   private Presenter presenter;

   ProjectLink projectLink = new ProjectLink();
   ProfileLink profileLink = new ProfileLink();
   StoryLink storyLink = new StoryLink();
   Span pulse = new Span(":)");

   @Override
   public void setup()
   {
      Div projectHeader = createProjectHeaderRow();
      Div body = createProjectBodyRow();

      content.add(projectHeader);
      content.add(body);
   }

   private Div createProjectHeaderRow()
   {
      Div projectHeader = new Div();
      projectHeader.setStyleName("row");
      Div left = new Div();
      left.setStyleName("span8 cols");
      Div right = new Div();
      right.setStyleName("span4 cols");

      projectHeader.add(left);
      projectHeader.add(right);

      BreadCrumbList breadcrumbs = new BreadCrumbList();
      breadcrumbs.push(new BreadCrumb(profileLink));
      breadcrumbs.push(new BreadCrumb(projectLink));
      breadcrumbs.push(new BreadCrumb(storyLink));

      Heading heading = new Heading(1);
      pulse.setStyleName("badge");
      heading.add(pulse);
      heading.add(new Span(" "));
      heading.add(breadcrumbs);
      heading.addStyleName("project-title");

      left.add(heading);
      return projectHeader;
   }

   private Div createProjectBodyRow()
   {
      Div projectHeader = new Div();
      projectHeader.setStyleName("row");
      Div left = new Div();
      left.setStyleName("span6 cols");
      Div right = new Div();
      right.setStyleName("span6 cols");

      projectHeader.add(left);
      projectHeader.add(right);

      return projectHeader;
   }

   @Override
   public void setStory(Story story)
   {
      this.project = story.getProject();
      this.profileLink.setProfile(story.getProject().getOwner());
      this.projectLink.setProject(story.getProject());
      this.storyLink.setStory(story);

      pulse.setInnerText(project.getOpenStories().size() + "");
   }

   /*
    * Getters & Setters
    */
   @Override
   public Presenter getPresenter()
   {
      return presenter;
   }

   @Override
   public void setPresenter(Presenter presenter)
   {
      this.presenter = presenter;
   }

   public Project getProject()
   {
      return project;
   }
}